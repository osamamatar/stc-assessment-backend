package com.stc.filemanagements.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AUTH_USER")
@Data
public class User {
	@Id
	@Column
	private String userName;
	@Column
	private String password;
	@Column
	private String email;

}
