package com.bdqn.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Smbms_Provider {
    private Integer id;//主键id
    private String proCode;//供应商编码
    private String proName;//供应商名称
    private String proDesc; //供应商详细描述
    private String proContact;//供应商联系人
    private String proPhone;//联系电话
    private String proAddress;//地址
    private String proFax;//传真
    private Integer createdBy;//创建者（userId）
    private Date creationDate;//创建时间
    private Date modifyDate;//更新时间
    private Integer modifyBy;//更新者（userId

    private List<Smbms_Bill> smbms_bills = new ArrayList<Smbms_Bill>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProContact() {
        return proContact;
    }

    public void setProContact(String proContact) {
        this.proContact = proContact;
    }

    public String getProPhone() {
        return proPhone;
    }

    public void setProPhone(String proPhone) {
        this.proPhone = proPhone;
    }

    public String getProAddress() {
        return proAddress;
    }

    public void setProAddress(String proAddress) {
        this.proAddress = proAddress;
    }

    public String getProFax() {
        return proFax;
    }

    public void setProFax(String proFax) {
        this.proFax = proFax;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public List<Smbms_Bill> getSmbms_bills() {
        return smbms_bills;
    }

    public void setSmbms_bills(List<Smbms_Bill> smbms_bills) {
        this.smbms_bills = smbms_bills;
    }

    @Override
    public String toString() {
        return "Smbms_Provider{" +
                "id=" + id +
                ", proCode='" + proCode + '\'' +
                ", proName='" + proName + '\'' +
                ", proDesc='" + proDesc + '\'' +
                ", proContact='" + proContact + '\'' +
                ", proPhone='" + proPhone + '\'' +
                ", proAddress='" + proAddress + '\'' +
                ", proFax='" + proFax + '\'' +
                ", createdBy=" + createdBy +
                ", creationDate=" + creationDate +
                ", modifyDate=" + modifyDate +
                ", modifyBy=" + modifyBy +
                '}';
    }
}
