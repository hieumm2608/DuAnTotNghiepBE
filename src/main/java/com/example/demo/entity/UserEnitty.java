package com.example.demo.entity;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEnitty {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@JsonDeserialize(using = UUIDDeserializer.class)
	UUID idUser;
	String fullname;
	String username;
	String password;
	boolean isAdmin = false;
	boolean isDeleted;
	@OneToMany(mappedBy = "userEnitty")
	@JsonIgnore
	List<OrderEntity> listOrderEntity;


}
