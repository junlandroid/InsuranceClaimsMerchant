package com.hy.junl.merchant.bean;

/**
 * Created by yuanjunliang on 2017/4/27.
 * description：登录返回结果
 */

public class LoginResultBean {
    private int InsuredID;
    private int AccountState;
    private String Tel;
    private String PKBExternalUserCode;
    private int IsHealthInsuranceUser;
    private int Authority;
    private String InsuredAuthority;
    private int UserType;
    private String Token;
    private int IsAgreeInsuredSignature;
    private String IDCardNo;


    public String getIDCardNo() {
        return IDCardNo;
    }

    public void setIDCardNo(String IDCardNo) {
        this.IDCardNo = IDCardNo;
    }

    public int getInsuredID() {
        return InsuredID;
    }

    public void setInsuredID(int insuredID) {
        InsuredID = insuredID;
    }

    public int getAccountState() {
        return AccountState;
    }

    public void setAccountState(int accountState) {
        AccountState = accountState;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getPKBExternalUserCode() {
        return PKBExternalUserCode;
    }

    public void setPKBExternalUserCode(String PKBExternalUserCode) {
        this.PKBExternalUserCode = PKBExternalUserCode;
    }

    public int getIsHealthInsuranceUser() {
        return IsHealthInsuranceUser;
    }

    public void setIsHealthInsuranceUser(int isHealthInsuranceUser) {
        IsHealthInsuranceUser = isHealthInsuranceUser;
    }

    public int getAuthority() {
        return Authority;
    }

    public void setAuthority(int authority) {
        Authority = authority;
    }

    public String getInsuredAuthority() {
        return InsuredAuthority;
    }

    public void setInsuredAuthority(String insuredAuthority) {
        InsuredAuthority = insuredAuthority;
    }

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int userType) {
        UserType = userType;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public int getIsAgreeInsuredSignature() {
        return IsAgreeInsuredSignature;
    }

    public void setIsAgreeInsuredSignature(int isAgreeInsuredSignature) {
        IsAgreeInsuredSignature = isAgreeInsuredSignature;
    }

    @Override
    public String toString() {
        return "LoginResultBean{" +
                "InsuredID=" + InsuredID +
                ", AccountState=" + AccountState +
                ", Tel='" + Tel + '\'' +
                ", PKBExternalUserCode='" + PKBExternalUserCode + '\'' +
                ", IsHealthInsuranceUser=" + IsHealthInsuranceUser +
                ", Authority=" + Authority +
                ", InsuredAuthority='" + InsuredAuthority + '\'' +
                ", UserType=" + UserType +
                ", Token='" + Token + '\'' +
                ", IsAgreeInsuredSignature=" + IsAgreeInsuredSignature +
                '}';
    }
}
