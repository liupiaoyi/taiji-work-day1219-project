/**   
 *       
 * 名称：MenuDTO   
 * 描述：   
 * 创建人：Administrator   
 * 创建时间：2017年12月8日 下午4:35:12 
 * @version       
 */ 

package cn.com.taiji.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import cn.com.taiji.domain.Menu;
import cn.com.taiji.domain.Role;

/**        
 * 类名称：MenuDTO   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017年12月8日 下午4:35:12 
 * @version      
 */

public class MenuDTO {
	private String id;

	private String menuDesc;

	private String text;

	private String url;
	
	private Menu menuParent;

	private List<MenuDTO> nodes;

	private Role role;

	private List<Role> roles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Menu getMenuParent() {
		return menuParent;
	}

	public void setMenuParent(Menu menuParent) {
		this.menuParent = menuParent;
	}

	public List<MenuDTO> getNodes() {
		return nodes;
	}

	public void setNodes(List<MenuDTO> nodes) {
		this.nodes = nodes;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "MenuDTO [id=" + id + ", menuDesc=" + menuDesc + ", text=" + text + ", url=" + url + ", menuParent="
				+ menuParent + ", nodes=" + nodes + ", role=" + role + ", roles=" + roles + "]";
	}

	public MenuDTO() {
		super();
	}

	public MenuDTO(String id, String menuDesc, String text, String url, Menu menuParent, List<MenuDTO> nodes, Role role,
			List<Role> roles) {
		super();
		this.id = id;
		this.menuDesc = menuDesc;
		this.text = text;
		this.url = url;
		this.menuParent = menuParent;
		this.nodes = nodes;
		this.role = role;
		this.roles = roles;
	}
}
