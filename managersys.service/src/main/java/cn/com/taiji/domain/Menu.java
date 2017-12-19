package cn.com.taiji.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.com.taiji.utils.Audited;

import java.util.List;


/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@NamedQuery(name="Menu.findAll", query="SELECT m FROM Menu m")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="menu_desc")
	private String menuDesc;

	@Column(name="menu_name")
	private String text;

	private String url;

	//bi-directional many-to-one association to Menu
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="menu_num")
	private Menu menuParent;

	//bi-directional many-to-one association to Menu
	@OneToMany(mappedBy="menuParent")
	private List<Menu> nodes;

	//bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(
		name="menu_role"
		, joinColumns={
			@JoinColumn(name="menu_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_id")
			}
		)
	private List<Role> roles;

	public Menu() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuDesc() {
		return this.menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public Menu getMenuParent() {
		return menuParent;
	}

	public void setMenuParent(Menu menuParent) {
		this.menuParent = menuParent;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public List<Menu> getNodes() {
		return nodes;
	}

	public void setNodes(List<Menu> nodes) {
		this.nodes = nodes;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Menu addMenus(Menu menus) {
		getNodes().add(menus);
		menus.setMenuParent(menus);

		return menus;
	}

	public Menu removeMenus(Menu menus) {
		getNodes().remove(menus);
		menus.setMenuParent(null);

		return menus;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Menu(String id, String menuDesc, String text, String url, Menu menuParent, List<Menu> nodes,
			List<Role> roles) {
		super();
		this.id = id;
		this.menuDesc = menuDesc;
		this.text = text;
		this.url = url;
		this.menuParent = menuParent;
		this.nodes = nodes;
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", menuDesc=" + menuDesc + ", text=" + text ;
	}

}