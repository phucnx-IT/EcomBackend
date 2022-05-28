package cybersoft.java16.ecom.user.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cybersoft.java16.ecom.common.model.BaseEntity;
import cybersoft.java16.ecom.role.model.UserGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@NoArgsConstructor
@SuperBuilder
@Table(name = "ecom_user")
public class EcomUser extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@Column(name = "email")
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "facebook")
	private String facebook;
	@Column(name = "provider")
	@Enumerated(EnumType.STRING)
	private Provider provider;
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "address")
	private String address;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name="delete")
	private boolean delete;
	@JsonIgnore
	@ManyToMany(mappedBy = "users",targetEntity = UserGroup.class)
	private Set<UserGroup> groups = new LinkedHashSet<UserGroup>();
}
