
package com.sjani.medieve.Models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class Medication implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("medicationtype")
    @Expose
    private String medicationtype;
    private final static long serialVersionUID = 9058435294046313554L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMedicationtype() {
        return medicationtype;
    }

    public void setMedicationtype(String medicationtype) {
        this.medicationtype = medicationtype;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("medicationtype", medicationtype).toString();
    }

}
