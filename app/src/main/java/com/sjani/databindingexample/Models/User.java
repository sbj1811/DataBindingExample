
package com.sjani.databindingexample.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sjani.databindingexample.Data.Converters;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity(tableName = "users")
public class User implements Serializable
{
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @ColumnInfo(name = "address1")
    @SerializedName("address1")
    @Expose
    private String address1;
    @ColumnInfo(name = "address2")
    @SerializedName("address2")
    @Expose
    private String address2;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uid")
    @SerializedName("uid")
    @Expose
    private String uid;
    @ColumnInfo(name = "sex")
    @SerializedName("sex")
    @Expose
    private String sex;
    @ColumnInfo(name = "dob")
    @SerializedName("dob")
    @Expose
    private String dob;
    @ColumnInfo(name = "disease")
    @SerializedName("disease")
    @Expose
    private String disease;
    @ColumnInfo(name = "medications")
    @SerializedName("medications")
    @Expose
    @TypeConverters(Converters.class)
    private List<Medication> medications = null;
    private final static long serialVersionUID = -4927336372938157623L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("address1", address1).append("address2", address2).append("uid", uid).append("sex", sex).append("dob", dob).append("disease", disease).append("medications", medications).toString();
    }

}
