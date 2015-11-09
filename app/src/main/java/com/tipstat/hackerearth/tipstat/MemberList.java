package com.tipstat.hackerearth.tipstat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

/**
 * Created by sivakishore.meka on 11/6/2015.
 */
@Generated("org.jsonschema2pojo")
public class MemberList {


    @SerializedName("members")
    @Expose
    private List<Member> members = new ArrayList<Member>();

    /**
     * @return The members
     */
    public List<Member> getMembers() {
        return members;
    }

    /**
     * @param members The members
     */
    public void setMembers(List<Member> members) {
        this.members = members;
    }

}
