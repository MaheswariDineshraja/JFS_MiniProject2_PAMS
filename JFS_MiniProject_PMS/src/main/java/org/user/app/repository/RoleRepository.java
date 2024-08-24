package org.user.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.user.app.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
}