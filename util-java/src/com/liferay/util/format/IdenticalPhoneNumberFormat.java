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

package com.liferay.util.format;

import com.liferay.portal.kernel.format.PhoneNumberFormat;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author     Brian Wing Shun Chan
 * @author     Manuel de la Peña
 * @deprecated {@link com.liferay.portal.format.IdenticalPhoneNumberFormatImpl}
 */
public class IdenticalPhoneNumberFormat implements PhoneNumberFormat {

	public String format(String phoneNumber) {
		return phoneNumber;
	}

	public String strip(String phoneNumber) {
		return phoneNumber;
	}

	public boolean validate(String phoneNumber) {
		if (Validator.isNull(phoneNumber)) {
			return false;
		}

		return true;
	}

}