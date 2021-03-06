/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.rolesadmin.search;

import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.MembershipPolicyUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Set;

import javax.portlet.RenderResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class UserRoleChecker extends RowChecker {

	public UserRoleChecker(RenderResponse renderResponse, Role role) {
		super(renderResponse);

		_role = role;
	}

	@Override
	public boolean isChecked(Object obj) {
		User user = (User)obj;

		try {
			return UserLocalServiceUtil.hasRoleUser(
				_role.getRoleId(), user.getUserId());
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	@Override
	public boolean isDisabled(Object obj) {
		User user = (User)obj;

		Set<Role> mandatoryRoles = MembershipPolicyUtil.getMandatoryRoles(user);

		if ((isChecked(user) && mandatoryRoles.contains(_role)) ||
			(!isChecked(user) &&
			 !MembershipPolicyUtil.isMembershipAllowed(_role, user))) {

			return true;
		}

		return super.isDisabled(obj);
	}

	private static Log _log = LogFactoryUtil.getLog(UserRoleChecker.class);

	private Role _role;

}