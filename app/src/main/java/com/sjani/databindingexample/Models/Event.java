
package com.sjani.databindingexample.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity(tableName = "events")
public class Event implements Serializable
{
    @ColumnInfo(name = "uid")
    @SerializedName("uid")
    @Expose
    private String uid;
    @ColumnInfo(name = "datetime")
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @ColumnInfo(name = "medication")
    @SerializedName("medication")
    @Expose
    private String medication;
    @ColumnInfo(name = "medicationtype")
    @SerializedName("medicationtype")
    @Expose
    private String medicationtype;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    private Long id;
    private final static long serialVersionUID = -1729393785723234284L;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getMedicationtype() {
        return medicationtype;
    }

    public void setMedicationtype(String medicationtype) {
        this.medicationtype = medicationtype;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("uid", uid).append("datetime", datetime).append("medication", medication).append("medicationtype", medicationtype).append("id", id).toString();
    }

}
