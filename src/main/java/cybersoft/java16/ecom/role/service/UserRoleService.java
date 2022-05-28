package cybersoft.java16.ecom.role.service;

import java.util.List;

import javax.validation.Valid;

import cybersoft.java16.ecom.role.dto.UserRoleDTO;
import cybersoft.java16.ecom.role.dto.UserRoleUpdateDTO;
import cybersoft.java16.ecom.role.dto.UserRoleWithProgramsDTO;

public interface UserRoleService {
	List<UserRoleDTO> findAllRoles();
	UserRoleDTO createNewRole(UserRoleDTO dto);
	UserRoleDTO updateRole(String roleId, UserRoleUpdateDTO dto);
	UserRoleDTO deleteRole(String roleId);
	UserRoleWithProgramsDTO addProgramsIntoRole(String programId, String roleId);
}
