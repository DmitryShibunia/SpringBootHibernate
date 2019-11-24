package com.dmitryshibunia.model;

import java.io.Serializable;
import java.util.Objects;

public class Message implements Serializable {

    private static final long serialVersionUID = 7526472295622776147L;
    private String filterFieldName;
    private String fieldToChangeName;
    private String filterFieldValue;
    private String fieldToChangeValue;

    public Message() {
    }

    public Message(String filterFieldName, String fieldToChangeName, String filterFieldValue, String fieldToChangeValue) {
        this.filterFieldName = filterFieldName;
        this.fieldToChangeName = fieldToChangeName;
        this.filterFieldValue = filterFieldValue;
        this.fieldToChangeValue = fieldToChangeValue;
    }

    public String getFilterFieldName() {
        return filterFieldName;
    }

    public void setFilterFieldName(String filterFieldName) {
        this.filterFieldName = filterFieldName;
    }

    public String getFieldToChangeName() {
        return fieldToChangeName;
    }

    public void setFieldToChangeName(String fieldToChangeName) {
        this.fieldToChangeName = fieldToChangeName;
    }

    public String getFilterFieldValue() {
        return filterFieldValue;
    }

    public void setFilterFieldValue(String filterFieldValue) {
        this.filterFieldValue = filterFieldValue;
    }

    public String getFieldToChangeValue() {
        return fieldToChangeValue;
    }

    public void setFieldToChangeValue(String fieldToChangeValue) {
        this.fieldToChangeValue = fieldToChangeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return filterFieldName.equals(message.filterFieldName) &&
                fieldToChangeName.equals(message.fieldToChangeName) &&
                filterFieldValue.equals(message.filterFieldValue) &&
                fieldToChangeValue.equals(message.fieldToChangeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filterFieldName, fieldToChangeName, filterFieldValue, fieldToChangeValue);
    }

    @Override
    public String toString() {
        return "Message{" +
                "filterFieldName='" + filterFieldName + '\'' +
                ", fieldToChangeName='" + fieldToChangeName + '\'' +
                ", filterFieldValue='" + filterFieldValue + '\'' +
                ", fieldToChangeValue='" + fieldToChangeValue + '\'' +
                '}';
    }
}
