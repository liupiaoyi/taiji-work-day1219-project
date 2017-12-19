/**   
 *       
 * 名称：TreeNodeConrtoller   
 * 描述：   
 * 创建人：Administrator   
 * 创建时间：2017年12月19日 上午10:31:33 
 * @version       
 */ 

package cn.com.taiji.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.taiji.domain.Menu;
import cn.com.taiji.dto.TreeNodeDTO;
import cn.com.taiji.service.MenuService;
import cn.com.taiji.service.UserService;

/**        
 * 类名称：TreeNodeConrtoller   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017年12月19日 上午10:31:33 
 * @version      
 */
@Controller
public class TreeNodeConrtoller {
	
	@RequestMapping("/node")
	public String toList() {
		return "tree";
	}
	
	
	@Autowired
	MenuService menuService;
	
	@RequestMapping("/showData")
	@ResponseBody
	public List<TreeNodeDTO> toTree() {
		List<TreeNodeDTO> lnotde = new ArrayList<>();
		TreeNodeDTO treeNode = menuService.treeNode("0");
		lnotde.add(treeNode);
		
		return lnotde;
	}
	
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/nodeuser")
	public String nodeuser() {
		System.out.println("lalalal");
		System.out.println(userService.findUserById("1"));
		return "index";
	}
	

}
