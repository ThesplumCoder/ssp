package com.thesplum.ssp.parser.statement.element;

import java.util.List;

/**
 * Models the form of each element found by parser.
 * 
 * @author Anderson Acuña
 */
public class Element {
    private String text;
    private ElementType elementType;
    private List<Element> elements;

    /**
     * Construct an element.
     * 
     * @param text Text to represent element.
     * @param elementType Type of element.
     */
    public Element(String text, ElementType elementType) {
        //
    }

    /**
     * Set the text of the element.
     * 
     * @param text Text to represent element.
     */
    public void setText(String text) {
        if (text != null && !text.isBlank()) {
            this.text = text;
        } else {
            throw new NullPointerException("The text of element can't be null nor empty.");
        }
    }

    /**
     * Get the text of element.
     * 
     * @return Text of the element.
     */
    public String getText() {
        return text;
    }

    /**
     * Set the type of element.
     * 
     * @param elementType Type of the element.
     */
    public void setElementType(ElementType elementType) {
        if (elementType != null) {
            this.elementType = elementType;
        } else {
            throw new NullPointerException("The type of element can't be null.");
        }
    }

    /**
     * Get the type of element.
     * 
     * @return Type of the element.
     */
    public ElementType getElementType() {
        return elementType;
    }

    /**
     * Set the list of elements.
     * 
     * @param elements List of internal elements.
     */
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    /**
     * Get the list of internal elements.
     * 
     * @return List of elements.
     */
    public List<Element> getElements() {
        return elements;
    }
}
