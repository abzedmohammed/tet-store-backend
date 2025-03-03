package com.tet_store.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.rmi.server.UID;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID usrId;
    private String usrNames;
    private String usrEmail;
    private String usrPhone;
    private String usrAvatar;
    private String usrAddress;
    private String usrCid;
}
