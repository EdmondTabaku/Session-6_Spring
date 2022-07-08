package com.example.session6.dto;

public class UserDTO {

    private String username;
    private String role;

    private UserDetailDTO userDetail;



    ///////////////////////////////////////////////////////////////////////
    ///////////////////////// GETTERS AND SETTERS /////////////////////////
    ///////////////////////////////////////////////////////////////////////

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserDetailDTO getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetailDTO userDetailDTO) {
        this.userDetail = userDetailDTO;
    }
}
