/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.chat.service.base;

import com.liferay.chat.model.Status;
import com.liferay.chat.service.EntryLocalService;
import com.liferay.chat.service.EntryLocalServiceFactory;
import com.liferay.chat.service.StatusLocalService;
import com.liferay.chat.service.persistence.EntryFinder;
import com.liferay.chat.service.persistence.EntryFinderUtil;
import com.liferay.chat.service.persistence.EntryPersistence;
import com.liferay.chat.service.persistence.EntryUtil;
import com.liferay.chat.service.persistence.StatusFinder;
import com.liferay.chat.service.persistence.StatusFinderUtil;
import com.liferay.chat.service.persistence.StatusPersistence;
import com.liferay.chat.service.persistence.StatusUtil;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import java.util.List;

/**
 * <a href="StatusLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class StatusLocalServiceBaseImpl implements StatusLocalService {
	public Status addStatus(Status status) throws SystemException {
		status.setNew(true);

		return statusPersistence.update(status, false);
	}

	public void deleteStatus(long statusId)
		throws PortalException, SystemException {
		statusPersistence.remove(statusId);
	}

	public void deleteStatus(Status status) throws SystemException {
		statusPersistence.remove(status);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return statusPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return statusPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	public Status getStatus(long statusId)
		throws PortalException, SystemException {
		return statusPersistence.findByPrimaryKey(statusId);
	}

	public Status updateStatus(Status status) throws SystemException {
		status.setNew(false);

		return statusPersistence.update(status, true);
	}

	public EntryLocalService getEntryLocalService() {
		return entryLocalService;
	}

	public void setEntryLocalService(EntryLocalService entryLocalService) {
		this.entryLocalService = entryLocalService;
	}

	public EntryPersistence getEntryPersistence() {
		return entryPersistence;
	}

	public void setEntryPersistence(EntryPersistence entryPersistence) {
		this.entryPersistence = entryPersistence;
	}

	public EntryFinder getEntryFinder() {
		return entryFinder;
	}

	public void setEntryFinder(EntryFinder entryFinder) {
		this.entryFinder = entryFinder;
	}

	public StatusPersistence getStatusPersistence() {
		return statusPersistence;
	}

	public void setStatusPersistence(StatusPersistence statusPersistence) {
		this.statusPersistence = statusPersistence;
	}

	public StatusFinder getStatusFinder() {
		return statusFinder;
	}

	public void setStatusFinder(StatusFinder statusFinder) {
		this.statusFinder = statusFinder;
	}

	protected void init() {
		if (entryLocalService == null) {
			entryLocalService = EntryLocalServiceFactory.getImpl();
		}

		if (entryPersistence == null) {
			entryPersistence = EntryUtil.getPersistence();
		}

		if (entryFinder == null) {
			entryFinder = EntryFinderUtil.getFinder();
		}

		if (statusPersistence == null) {
			statusPersistence = StatusUtil.getPersistence();
		}

		if (statusFinder == null) {
			statusFinder = StatusFinderUtil.getFinder();
		}
	}

	protected EntryLocalService entryLocalService;
	protected EntryPersistence entryPersistence;
	protected EntryFinder entryFinder;
	protected StatusPersistence statusPersistence;
	protected StatusFinder statusFinder;
}