package com.example.testdokka.model;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import java.util.List;


public class TestModelClass {

    @XmlElementRefs({
            @XmlElementRef(name = "NotOffered", namespace = "http://www.gaeb.de/GAEB_DA_XML/DA86/3.3", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> content;

}
