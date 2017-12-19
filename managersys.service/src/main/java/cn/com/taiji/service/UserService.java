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
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.com.taiji.domain.Role;
import cn.com.taiji.domain.RoleRepository;
import cn.com.taiji.domain.User;
import cn.com.taiji.domain.UserRepository;
import cn.com.taiji.dto.UserDTO;

/**        
 * 类名称：UserService   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017年12月8日 下午2:04:56 
 * @version      
 */
@Service
public class UserService {
	
	@Autowired
	UserRepository userRope;
	
	
	/**
	 * 新增用户
	 */
	@Transactional
	public UserDTO addUser(UserDTO userDTO) {
		//判断用户是否存在

			User user = new User();
			//将dto数据赋给实体类对象
			BeanUtils.copyProperties(userDTO,user);
//			user.updateAuditInfo();
			user.setId(UUID.randomUUID().toString());
			userRope.save(user);
		
		return userDTO;
	}
	
	/**
	 * 查询所有用户
	 */
	public List<UserDTO> allUser(){
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		//获取数据库中所有数据
		List<User> userList = userRope.findAll();
		for (User user : userList) {
			UserDTO userDTO = new UserDTO();
			//将实体类数据赋给 DTO
			BeanUtils.copyProperties(user,userDTO);
			userDTOList.add(userDTO);
		}
		return userDTOList;
	}
	
	
	/**
	 * 
	 * @Description: 分页
	 * @param id
	 * @return UserDTO  
	 * @throws
	 * @author Administrator
	 * @date 2017年12月11日
	 * 
	 */
	
	public Map getPage(int page,int pageSize,HashMap<String,String> orderMaps,HashMap<String,String> filters) {
		Page<User> pageContent;
		if (pageSize < 1)
			pageSize = 1;
		if (pageSize > 100)
			pageSize = 100;

		List<Order> orders = new ArrayList<Order>();
		if (orderMaps != null) {
			for (String key : orderMaps.keySet()) {
				if ("DESC".equalsIgnoreCase(orderMaps.get(key))) {
					orders.add(new Order(Direction.DESC, key));
				} else {
					orders.add(new Order(Direction.ASC, key));
				}
			}
		}

		PageRequest pageable;
		if (orders.size() > 0) {
			pageable = new PageRequest(page, pageSize, new Sort(orders));
		} else {
			pageable = new PageRequest(page, pageSize);
		}

		if (filters != null) {
			Specification<User> spec = new Specification<User>() {
				@Override
				public Predicate toPredicate(Root<User> root,
						CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> pl = new ArrayList<Predicate>();
					for (String key : filters.keySet()) {
						// TODO should change with operator, and according to value
						String value = filters.get(key);
						if ("enabled".equalsIgnoreCase(key)) {
							if ("true".equalsIgnoreCase(value)) {
								pl.add(cb.equal(root.get(key), true));
							} else if ("false".equalsIgnoreCase(value)) {
								pl.add(cb.equal(root.get(key), false));
							}
						} 	 if ("code".equalsIgnoreCase(key)) {
							if (value.length() > 0)
								pl.add(cb.like(root.<String> get(key), value + "%"));
						} 	 if ("userName".equalsIgnoreCase(key)) {
							if (value.length() > 0)
								pl.add(cb.like(root.get(key), value));
						}
					}
					return cb.and(pl.toArray(new Predicate[0]));
				}
			};
			pageContent = userRope.findAll(spec, pageable);
		} else {
			pageContent = userRope.findAll(pageable);
		}

		Map map = new HashMap();
		map.put("total", pageContent.getTotalElements());
		map.put("users", accountPage2Dto(pageContent));
		return map;
	}
	
	
//	public Map getPage2(Map searchParams) {
//		System.out.println("-----getPage-----");
//		
//		Map map = new HashMap();
//		int page=0;
//		int pageSize=2;
//		
//		Page<User> pageList;
//		if (searchParams != null && searchParams.size()>0 && searchParams.get("page")!=null) {
//			page = Integer.parseInt(searchParams.get("page").toString()) -1;
//		}
//		if (searchParams != null && searchParams.size()>0 && searchParams.get("pageSize")!=null) {
//			page = Integer.parseInt(searchParams.get("pageSize").toString());
//		}
//			
//		if (pageSize < 1)
//			pageSize = 1;
//		if (pageSize > 100)
//			pageSize = 100;
//
//		List<Map> orderMaps =  (List<Map>)searchParams.get("sort");
//		List<Order> orders = new ArrayList<Order>();
//		
//		if (orderMaps != null) {
//			for (Map m : orderMaps) {
//				if( m.get("field")==null ) 
//					continue;
//				String field = m.get("field").toString();
//				if(StringUtils.isEmpty(field)) {
//					String dir = m.get("dir").toString();
//					
//					if ("DESC".equalsIgnoreCase( dir )) {
//						orders.add(new Order(Direction.DESC, field));
//					} else {
//						orders.add(new Order(Direction.ASC, field));
//					}
//				}
//				
//				}
//			}
//			
//		PageRequest pageable;
//		if (orders.size() > 0) {
//			pageable = new PageRequest(page, pageSize, new Sort(orders));
//		} else {
//			Sort s = new Sort(Direction.ASC,"id");
//			pageable = new PageRequest(page, pageSize,s);//
//		}
//
//		Map filter = (Map)searchParams.get("filter");
//		
//		if (filter != null) {
//			final List<Map> filters = (List<Map>)filter.get("filters");
//			
//			Specification<User> spec = new Specification<User>() {
//				@Override
//				public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//					List<Predicate> p1 = new ArrayList<Predicate>();
//					for(Map f:filters) {
//						String field = f.get("field").toString().trim();
//						String value = f.get("value").toString().trim();
//						if(value!=null && value.length()>0) {
////							if("loginName".equalsIgnoreCase(field)) {
////								p1.add(cb.equal(root.<String> get(field), value));
////							}else 
//								if("userName".equalsIgnoreCase(field)) {
//								p1.add(cb.equal(root.<String> get(field), value));
//							}
////								else if("email".equalsIgnoreCase(field)) {
////								p1.add(cb.equal(root.<String> get(field), value));
////							}
//						}
//					}
////					p1.add(cb.equal(root.<Integer> get("flag"), 1));
////					p1.add(cb.equal(root.<Integer> get("state"), 1));
//					return cb.and(p1.toArray(new Predicate[0]));
//				}
//			};
//			
//			//pageList = userRope.findAll(spec, pageable);
//			pageList = userRope.findAll(pageable);
//			
//			System.out.println("spec===="+spec.toString());
//			System.out.println("pageable===="+pageable);
//		} else {
//			pageList = userRope.findAll(pageable);
//		}
//		
//		
//		System.out.println("pageList====");
//		pageList.forEach(System.out::println);
//		
//		
//		map.put("total", pageList.getTotalElements());
//		map.put("users", accountPage2Dto(pageList));
//		return map;
//	}

	/**
	 * @Description: 简要进行方法说明，并对基础数据类型的参数和返回值加以说明
	 * @param pageList
	 * @return Object  
	 * @throws
	 * @author Administrator
	 * @date 2017年12月11日
	 */
	private List<UserDTO> accountPage2Dto(Page<User> pageList) {
		List<UserDTO> pageListDto = new ArrayList<UserDTO>();
		List<User> userList = pageList.getContent();
		
		for (User user : userList) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user,userDTO);
			pageListDto.add(userDTO);
		}
		return pageListDto;
	}

	//按id查询
	public UserDTO findUserById(String id) {
		UserDTO userDTO = new UserDTO();
		//获取用户信息
		User user = userRope.findOne(id);
		//将实体类数据赋给 DTO
		BeanUtils.copyProperties(user,userDTO);
		return userDTO;
	}
	
	
	//按姓名查询
	public List<UserDTO> findUserByUsername(String userName) {
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		//获取用户信息
		List<User> users = userRope.findUserByUsername(userName);
		
		//将实体类数据赋给 DTO
		for (User user : users) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user,userDTO);
			userDTOList.add(userDTO);
		}
		return userDTOList;
	}
	
	
	
	/**
	 * 修改用户信息
	 */
	public void updateUser(UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(user,userDTO);
		
		userRope.save(user);
		
	}
	
	
	/**
	 * 权限
	 */
	@Autowired
	RoleRepository roleRope;
	
	public void getRoles(UserDTO userDTO) {
		//获取相应实体
		User user = userRope.findOne(userDTO.getId());
		//User user = userRope.findOne("1");
		//获取相应权限
		//Role role = roleRope.findOne(id);
		Role role = roleRope.findOne("111");
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		user.setRoles( roles );
		
		userRope.save(user);
	
	}
	
	
	
	/**
	 * 删除用户--ok
	 */
	public void deleteUser(String id) {
		userRope.delete(id);
	}
	
	/**
	 * entity2dto
	 */
	public UserDTO entity2dto(User user) {
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user,userDTO);
		
		return userDTO;
	}
	
	
}
