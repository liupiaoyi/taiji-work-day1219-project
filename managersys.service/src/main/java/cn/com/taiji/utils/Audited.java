/**   
 *       
 * 名称：Audited   
 * 描述：   
 * 创建人：Administrator   
 * 创建时间：2017年12月11日 下午12:51:14 
 * @version       
 */ 

package cn.com.taiji.utils;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**        
 * 类名称：Audited   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017年12月11日 下午12:51:14 
 * @version      
 */

@MappedSuperclass 	//不是完整的数据库表中不会映射
public class Audited {
	
	@Id
	@Column(name="id")
	private String id;
	
	@CreatedBy
	protected String createdBy;
	
	@CreatedDate
	protected Calendar createdDate;
	
	@LastModifiedBy
	protected String modifiedBy;
	
	@LastModifiedDate
	protected Calendar modifiedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Calendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Calendar getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Calendar modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	@Override
	public String toString() {
		return "Audited [id=" + id + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + "]";
	}

	/**
	 * 当前更新记录
	 */
	public void updateAuditInfo() {
		SecurityContext context = SecurityContextHolder.getContext();
		if(null!=context.getAuthentication() && !context.getAuthentication().isAuthenticated() ) {
			UserDetails userDetails = (UserDetails) context.getAuthentication().getPrincipal();
			
			setCreatedBy(null==userDetails?"":userDetails.getUsername());
		}
		setModifiedDate(Calendar.getInstance());
		
	}
	
	
	
}
