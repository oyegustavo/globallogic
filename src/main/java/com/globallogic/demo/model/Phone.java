package com.globallogic.demo.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="phones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phone implements Serializable{
	private static final long serialVersionUID = -6931983352881527225L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
	private Long id;
	private Long number;
	private Integer cityCode;
	private Integer countryCode;
	@JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
	private User user;
}
