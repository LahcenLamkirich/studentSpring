//package com.enset.studentcrud.security.entities;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class AppUser {
//    @Id
//    private String userId;
//    @Column(unique = true)
//    private String username;
//    private String password;
//    private Boolean active;
//    @OneToMany(fetch = FetchType.EAGER)
//    private List<AppRole> appRoles = new ArrayList<>();
//}
