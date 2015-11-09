package com.tipstat.hackerearth.tipstat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

/**
 * Created by sivakishore.meka on 11/6/2015.
 */


@Generated("org.jsonschema2pojo")
public class Member {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("ethnicity")
    @Expose
    private String ethnicity;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("is_veg")
    @Expose
    private String isVeg;
    @SerializedName("drink")
    @Expose
    private String drink;
    @SerializedName("image")
    @Expose
    private String image;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob The dob
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The ethnicity
     */
    public String getEthnicity() {
        return ethnicity;
    }

    /**
     * @param ethnicity The ethnicity
     */
    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    /**
     * @return The weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     * @param weight The weight
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * @return The height
     */
    public String getHeight() {
        return height;
    }

    /**
     * @param height The height
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * @return The isVeg
     */
    public String getIsVeg() {
        return isVeg;
    }

    /**
     * @param isVeg The is_veg
     */
    public void setIsVeg(String isVeg) {
        this.isVeg = isVeg;
    }

    /**
     * @return The drink
     */
    public String getDrink() {
        return drink;
    }

    /**
     * @param drink The drink
     */
    public void setDrink(String drink) {
        this.drink = drink;
    }

    /**
     * @return The image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image The image
     */
    public void setImage(String image) {
        this.image = image;
    }

}
