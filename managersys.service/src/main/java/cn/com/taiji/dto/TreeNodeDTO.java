/**   
 *       
 * 名称：TreeNodeDTO   
 * 描述：   
 * 创建人：Administrator   
 * 创建时间：2017年12月19日 上午9:34:30 
 * @version       
 */ 

package cn.com.taiji.dto;

import java.util.ArrayList;
import java.util.List;

import cn.com.taiji.domain.Menu;

/**        
 * 类名称：TreeNodeDTO   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017年12月19日 上午9:34:30 
 * @version      
 */

public class TreeNodeDTO {
	
	private String id;
	private String text;
	
	private String href;
	private List nodes;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
	public List getNodes() {
		return nodes;
	}
	public void setNodes(List nodes) {
		this.nodes = nodes;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
	public TreeNodeDTO() {
		super();
	}
	
	
	
	/**
	 * 
	 * Description: 构造方法描述<menuDTO>
	 * @param text
	 * @param href
	 * @param nodes
	 * @author Administrator
	 */

//	public TreeNodeDTO(MenuDTO menuDTO) {
//		if( menuDTO.getId()!=null && menuDTO.getId().length()>0 ) {
//			this.setId( menuDTO.getId() );
//		}
//		if( menuDTO.getText()!=null && menuDTO.getText().length()>0 ) {
//			this.setText( menuDTO.getText() );
//		}
//		if( menuDTO.getUrl()!=null && menuDTO.getUrl().length()>0 ) {
//			this.setHref( menuDTO.getUrl() );
//		}
//		
//		List<MenuDTO> child = menuDTO.getNodes();
//		if( !child.isEmpty() && child.size()>0 ) {//有子菜单
//			for (MenuDTO menuChild : child) {
//				this.nodes.add( new TreeNodeDTO(menuChild) );
//			}
//		}
//		
//		
//	}

//	public TreeNodeDTO toTreeDto(Menu menu) {
//		TreeNodeDTO treeDto = new TreeNodeDTO();
//		
//		if( menu.getId()!=null && menu.getId().length()>0 ) {
//			this.setId( menu.getId() );
//		}
//		if( menu.getText()!=null && menu.getText().length()>0 ) {
//			this.setText( menu.getText() );
//		}
//		if( menu.getUrl()!=null && menu.getUrl().length()>0 ) {
//			this.setHref( menu.getUrl() );
//		}
//		
//		List<TreeNodeDTO> list = new ArrayList<>();
//		for ( Menu menuChild : menu.getNodes() ) {
//			if(menuChild!=null) {
//				TreeNodeDTO treeDto2 = toTreeDto(menuChild);
//				list.add(treeDto2);
//			}
//		}
//		treeDto.setNodes(list);	
//		return treeDto;
//	}
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "TreeNodeDTO [id=" + id + ", text=" + text + ", href="+ href + ", nodes=" + nodes + "]";
	}

}
