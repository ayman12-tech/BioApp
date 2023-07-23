package com.example.test.RestApi;

import com.google.gson.annotations.SerializedName;

public class tokenResponse {
    @SerializedName("token")
    private String token;

    @SerializedName("refreshToken")
    private String refreshToken;

//    @SerializedName("userName")
//    private String userName;

    public tokenResponse()
    {

    }
    public void setAccess_token(String Token) {this.token=token;}
    public void setToken_type(String RefreshToken){this.refreshToken = refreshToken;}
    //public void setUserName(String userName){this.userName=userName;}

    public String getAccess_token() {return token;}
    public String getToken_type() {return refreshToken;}
    //public String getUserName() {return userName;}
}
