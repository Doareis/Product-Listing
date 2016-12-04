package br.com.productlisting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 *
 * Class for Entity Image
 *
 * @Author: Douglas Reis
 * @Version: 1.0
 * @Since 2016-11-23
 */

@Entity
@Table(name="image")
public class Image  {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;

	@NotNull
	@Column(name="type", nullable=false)
	private String type;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_id", nullable=false)
	private Product product;

	/*
	 * @return image id
	 *
	 **/
	public long getId() {
		return id;
	}

	/*
	 * @param image id
	 *
	 **/
	public void setId(long id) {
		this.id = id;
	}

	/*
	 * @return image type
	 *
	 **/
	public String getType() {
		return type;
	}

	/*
	 * @param image type
	 *
	 **/
	public void setType(String type) {
		this.type = type;
	}

	/*
	 * @return image product
	 *
	 **/
	public Product getProduct() {
		return product;
	}

	/*
	 * @param image product
	 *
	 **/
	public void setProduct(Product product) {
		this.product = product;
	}
}
