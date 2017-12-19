/**   
 *       
 * 名称：UserService   
 * 描述：   
 * 创建人：Administrator   
 * 创建时间：2017年12月8日 下午2:04:56 
 * @version       
 */ 

package cn.com.taiji.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.domain.Menu;
import cn.com.taiji.domain.MenuRepository;
import cn.com.taiji.dto.MenuDTO;
import cn.com.taiji.dto.TreeNodeDTO;

/**        
 * 类名称：UserService   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017年12月8日 下午2:04:56 
 * @version      
 */
@Service
public class MenuService {
	
	@Autowired
	MenuRepository menuRope;
	
	
	/**
	 * 查询所有用户
	 */
	public List<MenuDTO> allMenu(){
		List<MenuDTO> menuDTOList = new ArrayList<MenuDTO>();
		
		//获取数据库中所有数据
		List<Menu> menuList = menuRope.findAll();
		for (Menu menu : menuList) {
			MenuDTO menuDTO = new MenuDTO();
			
			//将实体类数据赋给 DTO
			BeanUtils.copyProperties(menu,menuDTO);
//			if( menu.getMenuParent()!=null ) {
//				menuDTO.setMenu( menu.getMenuChild() );
//			}
			menuDTOList.add(menuDTO);
		}
		
		return menuDTOList;
	}
	
	
	//按id查询
	public MenuDTO findMenuById(String id) {
		MenuDTO menuDTO = new MenuDTO();
		//获取用户信息
		Menu menu = menuRope.findOne(id);
		//将实体类数据赋给 DTO
		BeanUtils.copyProperties(menu,menuDTO);
		
		return menuDTO;
	}
	
	
	
	/**
	 * 添加菜单
	 */
	@Transactional
	public MenuDTO addMenu(MenuDTO menuDTO) {
		
		Menu findOne2 = menuRope.findOne(menuDTO.getId());
		Menu findOne = findOne2;
		if( findOne==null ) {
			Menu menu = new Menu();
			//将dto数据赋给实体类对象
			BeanUtils.copyProperties(menuDTO,menu);
			//根据页面传来的id值，获取menu
//			menu.setMenuChild( menuRope.findOne( menuDTO.getMenu() ).getId() );
			
			menuRope.save(menu);
		}
		return menuDTO;
	}


	
	/**
	 * 删除菜单
	 */
	public void deleteMenu(String id) {
		menuRope.delete(id);
	}
	
	
	/**
	 * 修改
	 */
	public void updateMenu(MenuDTO menuDTO) {
		//转成实体类数据
		Menu menu = new Menu();
		BeanUtils.copyProperties(menuDTO,menu);
		
//		int n = menuRope.updateMenu(menu.getMenuParent().getId(), menu.getMenuDesc(), menu.getUrl(),
//				menuRope.findOne( menuDTO.getId() ), menu.getId() );
		
//		System.out.println(n);
	}
	
	
	
	/**
	 * node
	 */
	public TreeNodeDTO treeNode(String id){
		//查询根节点
		Menu parent = menuRope.findOne(id);
		TreeNodeDTO node = new TreeNodeDTO();
		TreeNodeDTO treeDto = toTreeDto(parent);
//		node.setId(menuDTO.getId());
//		node.setText(menuDTO.getText());
//		List<MenuDTO> nodes = menuDTO.getNodes();
//		node.setNodes(nodes);

		return treeDto;
	}
	
	public TreeNodeDTO toTreeDto(Menu menu) {
		TreeNodeDTO treeDto = new TreeNodeDTO();
		treeDto.setId(menu.getId());
		treeDto.setText(menu.getText());
		treeDto.setHref(menu.getUrl());
		
		List<TreeNodeDTO> list = new ArrayList<>();
		for ( Menu menuChild : menu.getNodes() ) {
			if(menuChild!=null) {
				TreeNodeDTO treeDto2 = toTreeDto(menuChild);
				list.add(treeDto2);
			}
		}
		treeDto.setNodes(list);	
		return treeDto;
	}
	
	
	
	
	

}
