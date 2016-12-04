package br.com.productlisting.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
/*
 *
 * Class for Entity Product
 *
 *@Author: Douglas Reis
 *@Version: 1.0
 *@Since 2016-11-23
 */
@Entity@Table(name="product")
/*@NamedNativeQueries({
	@NamedNativeQuery(
			name = "getProductByIdExcludingRelationships",
			query = )
})*/
public class Product {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;

	@Column(name="name")
	@NotNull
	private String name;

	@Column(name="description")
	@NotNull
	private String description;

	@JsonInclude(Include.NON_NULL)
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="parent_product_id")
	private Product parentProduct;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("images")
	@OneToMany(mappedBy = "product", targetEntity = Image.class, 
		cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Image> images;

	// getters and setters

	/*
	 *
	 *@return product id
	 */
	public long getId() {
		return id;
	}

	/*
	 * @param product id
	 *
	 **/
	public void setId(long id) {
		this.id = id;
	}


	/*
	 *
	 *@return product name
	 */
	public String getName() {
		return name;
	}

	/*
	 *
	 *@param  product name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 *
	 *@return  product description
	 */
	public String getDescription() {
		return description;
	}

	/*
	 *
	 *@param  product description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 *
	 *@return  product parent
	 */
	public Product getParentProduct() {
		return parentProduct;
	}

	/*
	 *
	 *@param  product description
	 */
	public void setParentProduct(Product parentProduct) {
		this.parentProduct = parentProduct;
	}

	/*
	 *
	 *@return  product images
	 */
	public List<Image> getImages() {
		if(images == null){
			images = new ArrayList<Image>();
		}
		return images;
	}

	/*
	 *
	 *@param  product images
	 */
	public void setImages(List<Image> images) {
		this.images = images;
	}
}
