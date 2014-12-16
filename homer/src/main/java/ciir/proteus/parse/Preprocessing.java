/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package ciir.proteus.parse;
import java.io.*;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;
import javax.xml.namespace.QName;

import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.XMLEventFactory;

/**
 *
 * @author bzifkin the purpose of this class is twofold: 1) it will trim from
 * the document all words outside the margins 2) it will fix all index numbering
 * so that the index pages have a constant monotonic increase.
 */
public class Preprocessing {

    public Preprocessing() {
    }

    int marginUp = 0; //these are the margins which determines if a token is eligible or not
    int marginDown = 0;
    int marginLeft = 0;
    int marginRight = 0;
    int quarterInch = 0;
    int xOne = 0; //coordinates of the words
    int yOne = 0;
    int xTwo = 0;
    int yTwo = 0;
    int line =0;
    int properPNum = 1;
    boolean mapCheck = false;
    boolean paramCheck = false;
    boolean objectCheck = false;
    File thef;
    static String newFileName = "";
    static XMLInputFactory factory = XMLInputFactory.newInstance();  //initiate readers/writers/factories
    static XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();

    XMLEventReader reader = null;
    XMLEventWriter writer;
    XMLEventFactory eventFactory = null;
    public List<Pages> pageList = new ArrayList<Pages>();
//ask john what this does

    public Preprocessing(File ifp) throws XMLStreamException, FileNotFoundException {
        this.thef = ifp;
        newFileName = ifp.getName().substring(0, ifp.getName().indexOf("_") + 1);

        InputStream str = new FileInputStream(ifp);
        try {

            reader = factory.createXMLEventReader(str);
            factory.setXMLResolver(factory.getXMLResolver());
        } catch (XMLStreamException e) {
            e.printStackTrace();
            System.out.println("There was an XML Stream Exception, whatever that means");
        }
        try {
            // CaT == Clean and Trimmed
            writer = outputFactory
                    .createXMLEventWriter(new FileOutputStream(newFileName + "CaT.xml"), "UTF-8");

        } catch (XMLStreamException ex) {
            ex.printStackTrace();
            System.out.println("There was an XML Stream Exception, whatever that means for writer");
        }

        eventFactory = XMLEventFactory.newInstance();
        StartDocument startDocument = eventFactory.createStartDocument();

        writer.add(startDocument);
        XMLEvent dtd = eventFactory.createDTD("<!DOCTYPE DjVuXML>");
        writer.add(dtd);

    }

    public void getPageHeightAndWidth(StartElement se) {
        int pHeight = -1;
        int pWidth = -1;
        Iterator<Attribute> attributes = se.getAttributes();
        while (attributes.hasNext()) {

            Attribute attribute = attributes.next();
            //grabs some attributes set the value to variables

            if (attribute.getName().toString().equals("height")) {
                pHeight = Integer.valueOf(attribute.getValue());
            }

            if (attribute.getName().toString().equals("width")) {
                pWidth = Integer.valueOf(attribute.getValue());
            }
        }
        //System.out.println("pHeigt: " + pHeight);
        //System.out.println("pWidth : " + pWidth);
        calculateMargins(pHeight, pWidth);

    }

    public void setQI(StartElement se) {
        int dpi = -1;
        Iterator<Attribute> attributes = se.getAttributes();
        while (attributes.hasNext()) {

            Attribute attribute = attributes.next();

            if (attribute.getValue().equals("DPI")) {

                dpi = Integer.valueOf(attributes.next().getValue());
            }
            //System.out.println("dpi: " + dpi);
            quarterInch = (int) (dpi * .25);

        }
        //System.out.println("quarter inch: " + quarterInch);
    }

    //calculate margins to be outer 10%
    public void calculateMargins(int ph, int pw) {
        marginUp = (int) (ph * .1);

        marginDown = ph - ((int) (ph * .1));

        marginLeft = (int) (pw * .1);

        marginRight = pw - ((int) (pw * .1));

    }
//this grabs the amount of pixels in an inch, allowing us to add a 
// quarter inch of wiggle room to the margins to account for
// noise, uneven scanning, etc

    public void getWordCoords(StartElement se) {
        Iterator<Attribute> coord = se.getAttributes();
        Attribute position = coord.next();

        StringTokenizer st = new StringTokenizer(position.getValue(), ",");

        xOne = Integer.valueOf(st.nextToken());
        yOne = Integer.valueOf(st.nextToken());
        xTwo = Integer.valueOf(st.nextToken());
        yTwo = Integer.valueOf(st.nextToken());
        line = Integer.valueOf(st.nextToken());

    }

    public int getPageIndex(StartElement se) {
        Iterator<Attribute> coord = se.getAttributes();
        Attribute position = coord.next();

        String st = position.getValue();
        // System.out.println(st);
        int start = st.indexOf("_");
        int end = st.indexOf(".");
        // System.out.println(st.substring(start+1, end));
        int index = Integer.valueOf(st.substring(start + 1, end));
        return index;

    }
//see if in margins

    public boolean inMargin(int xone, int yone, int xtwo, int ytwo) {
        if (xone <= (marginLeft + quarterInch) || yone >= (marginDown - quarterInch) || xtwo >= (marginRight - quarterInch) || ytwo <= (marginUp + quarterInch)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkIndex(StartElement se) {
        Iterator<Attribute> it = se.getAttributes();

        if ("OBJECT".equals(se.getName().getLocalPart())) {

            while (it.hasNext()) {
                Attribute atr = it.next();
                if (atr.getName().toString().equals("usemap")) {
                    objectCheck = true;
                    String st = atr.getValue();
                    int start = st.indexOf("_");
                    int end = st.indexOf(".");
                    int index = Integer.valueOf(st.substring(start + 1, end));
                    if (index != properPNum) {

                        return false;
                    } else {

                        return true;

                    }
                }
            }

        }
        if ("PARAM".equals(se.getName().getLocalPart())) {

            while (it.hasNext()) {
                Attribute atr = it.next();
                if (atr.getName().toString().equals("name") && atr.getValue().equals("PAGE")) {
                    paramCheck = true;
                    String st = it.next().getValue();
                    int start = st.indexOf("_");
                    int end = st.indexOf(".");
                    int index = Integer.valueOf(st.substring(start + 1, end));
                    if (index != properPNum) {

                        return false;
                    } else {

                        return true;
                    }
                }
                return true;
            }

        }
        if ("MAP".equals(se.getName().getLocalPart())) {
            while (it.hasNext()) {
                Attribute atr = it.next();
                if (atr.getName().toString().equals("name")) {
                    mapCheck = true;
                    String st = atr.getValue();
                    int start = st.indexOf("_");
                    int end = st.indexOf(".");
                    int index = Integer.valueOf(st.substring(start + 1, end));
                    if (index != properPNum) {

                        return false;
                    } else {

                        return true;
                    }
                }
            }

        }
        return false;

    }

    public StartElement makeNewElement(StartElement se) {
        Iterator<Attribute> it = se.getAttributes();
        Iterator blah = null;
        List temp = new ArrayList();
        String a = "";
        String b = "";
        StartElement fresh = null;

        if ("OBJECT".equals(se.getName().getLocalPart())) {

            while (it.hasNext()) {
                Attribute atr = it.next();

                temp.add(atr);
            }
            for (int i = 0; i <= temp.size() - 1; i++) {
                Attribute fix = (Attribute) temp.get(i);
                if (fix.getName().toString().equals("usemap")) {

                    String st = fix.getValue();
                    a = st.substring(0, st.indexOf("_") + 1);
                    b = st.substring(st.indexOf("."), st.length());

                    Attribute x = eventFactory.createAttribute("usemap", a + Integer.toString(properPNum) + b);

                    temp.remove(i);
                    temp.add(i, x);

                }
            }

            fresh = eventFactory.createStartElement(new QName("OBJECT"), temp.iterator(), blah);

            return fresh;

        }
        if ("PARAM".equals(se.getName().getLocalPart())) {

            while (it.hasNext()) {
                Attribute atr = it.next();

                temp.add(atr);
            }
            for (int i = 0; i <= temp.size() - 1; i++) {
                Attribute fix = (Attribute) temp.get(i);
                if (fix.getValue().equals("PAGE")) {
                    fix = (Attribute) temp.get(i + 1);
                    String st = fix.getValue();
                    a = st.substring(0, st.indexOf("_") + 1);
                    b = st.substring(st.indexOf("."), st.length());

                    Attribute x = eventFactory.createAttribute("value", a + Integer.toString(properPNum) + b);

                    temp.remove(i + 1);
                    temp.add(i + 1, x);

                }
            }

            fresh = eventFactory.createStartElement(new QName("PARAM"), temp.iterator(), blah);
            return fresh;

        }

        if ("MAP".equals(se.getName().getLocalPart())) {

            while (it.hasNext()) {
                Attribute atr = it.next();

                temp.add(atr);
            }
            for (int i = 0; i <= temp.size() - 1; i++) {
                Attribute fix = (Attribute) temp.get(i);
                if (fix.getName().toString().equals("name")) {

                    String st = fix.getValue();
                    a = st.substring(0, st.indexOf("_") + 1);
                    b = st.substring(st.indexOf("."), st.length());

                    Attribute x = eventFactory.createAttribute("name", a + Integer.toString(properPNum) + b);

                    temp.remove(i);
                    temp.add(i, x);

                }
            }

            fresh = eventFactory.createStartElement(new QName("MAP"), temp.iterator(), blah);
            return fresh;

        }
        return fresh;

    }

    public void increment() {
        if (mapCheck == true && paramCheck == true && objectCheck == true) {
            properPNum++;
            mapCheck = false;
            paramCheck = false;
            objectCheck = false;

        }

    }

    public List<Pages> trim(XMLEvent event) throws FileNotFoundException, XMLStreamException {
        XMLEventWriter writer = this.writer;

        while (reader.hasNext()) {
            increment();
            event = reader.nextEvent();

            if (event.isStartElement()) { //first it looks for start elements
                StartElement se = event.asStartElement();
                if ("BODY".equals(se.getName().getLocalPart())) {
                    writer.add(se);

                } else if ("PARAM".equals(se.getName().getLocalPart())) {
                    if (checkIndex(se)) {
                        writer.add(se);
                    } else {
                        writer.add(makeNewElement(se));
                    }
                    if (quarterInch >= 0) {
                        setQI(se);
                    }
                } else if ("MAP".equals(se.getName().getLocalPart())) {
                    if (checkIndex(se)) {
                        writer.add(se);
                    } else {
                        writer.add(makeNewElement(se));
                    }
                    Pages page = new Pages();
                    page.indexNum = properPNum;
                    pageList.add(page);

                } else if ("OBJECT".equals(se.getName().getLocalPart())) {
                    getPageHeightAndWidth(se);
                    if (checkIndex(se)) {
                        writer.add(se);
                    } else {
                        writer.add(makeNewElement(se));
                    }

                } else if ("LINE".equals(se.getName().getLocalPart())) {
                    writer.add(se);

                } else if ("WORD".equals(se.getName().getLocalPart())) {
                    getWordCoords(se);

                    if (inMargin(xOne, yOne, xTwo, yTwo)) { //check to see if in margins, if it is the end tag is written immediately, otherwise nothing is written
                        Word word = new Word();
                        word.xOne = xOne;
                        word.yOne = yOne;
                        word.xTwo = xTwo;
                        word.yTwo = yTwo;
                        word.line = line;

                        word.text = reader.getElementText();

                        EndElement wordEnd = eventFactory.createEndElement("", "", "WORD");
                        writer.add(se);
                        Characters characters = eventFactory.createCharacters(word.text);
                        writer.add(characters);
                        writer.add(wordEnd);
                        if (pageList.size() == 0) {

                            Pages page = new Pages();
                            page.indexNum = word.index = 0;
                            page.wordsOnPage.add(word);
                            pageList.add(page);

                        } else if (pageList.size() > 0) {

                            Pages lastPage = pageList.get(pageList.size() - 1);
                            word.index = lastPage.indexNum;
                            lastPage.wordsOnPage.add(word);

                        }

                    }

                }

            } else if (event.isEndElement()) {

                EndElement ee = event.asEndElement();
                if ("BODY".equals(ee.getName().getLocalPart())) {
                    writer.add(ee);

                } else if ("PARAM".equals(ee.getName().getLocalPart())) {
                    writer.add(ee);
                } else if ("MAP".equals(ee.getName().getLocalPart())) {
                    writer.add(ee);
                } else if ("OBJECT".equals(ee.getName().getLocalPart())) {
                    writer.add(ee);
                } else if ("LINE".equals(ee.getName().getLocalPart())) {
                    writer.add(ee);
                }

            }

        }
        writer.flush();
        writer.close();

        return pageList;
    }

    public List<Pages> getPageList(XMLEvent event) throws XMLStreamException {
        while (reader.hasNext()) {
properPNum++;
            event = reader.nextEvent();
            if (event.isStartElement()) { //first it looks for start elements
                StartElement se = event.asStartElement();
                if ("MAP".equals(se.getName().getLocalPart())) {

                    Pages page = new Pages();
                    page.indexNum = properPNum;
                    pageList.add(page);
                }
                if ("WORD".equals(se.getName().getLocalPart())) {
                    getWordCoords(se);

                    Word word = new Word();
                    word.xOne = xOne;
                    word.yOne = yOne;
                    word.xTwo = xTwo;
                    word.yTwo = yTwo;

                    word.text = reader.getElementText();

                    if (pageList.size() == 0) {

                        Pages page = new Pages();
                        page.indexNum = word.index = 0;
                        page.wordsOnPage.add(word);
                        pageList.add(page);

                    } else if (pageList.size() > 0) {

                        Pages lastPage = pageList.get(pageList.size() - 1);
                        word.index = lastPage.indexNum;
                        lastPage.wordsOnPage.add(word);

                    }

                }
            }
        }
        
        return pageList;
    }
    /*
     public String findJumps(XMLEvent event) throws XMLStreamException {
     String jumps = "For book " + thef.getName() + " there are jumps between: ";
     int currIndex = -1;
     while (reader.hasNext()) {
     event = reader.nextEvent();
     if (event.isStartElement()) { //first it looks for start elements
     StartElement se = event.asStartElement();
     if ("MAP".equals(se.getName().getLocalPart())) {
     Iterator<Attribute> attributes = se.getAttributes();
     while (attributes.hasNext()) {

     Attribute attribute = attributes.next();
     //grabs some attributes set the value to variables

     if (attribute.getName().toString().equalsIgnoreCase("name")) {
     String temp = attribute.getValue();
     int start = temp.indexOf("_");
     int end = temp.indexOf(".");
     int index = Integer.valueOf(temp.substring(start + 1, end));
     if (index != currIndex + 1) {
     jumps = jumps + currIndex + " ---> " + index + "\n";
     }
     currIndex = index;
     }
     }
     }

     }
     }
     return jumps;
     }
     */
}
