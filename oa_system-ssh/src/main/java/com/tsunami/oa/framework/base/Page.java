package com.tsunami.oa.framework.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class Page<T> {
	// ��һҳ
		private int pageNo;

		// ��ǰҳ
		private int currentPage = 1;

		// ÿҳ������
		private int pageSize = 20;

		// ������
		private int totalCount;

		// ��ҳ��
		private int pageCount;

		// ��¼
		private List<?> results;

		public Page() {

		}

		public Page(HttpServletRequest request) {
			try {
				String page = request.getParameter("page");
				String rows = request.getParameter("rows");
				if (StringUtils.isNotBlank(page))
					currentPage = Integer.valueOf(page);
				if (StringUtils.isNotBlank(rows))
					pageSize = Integer.valueOf(rows);
			} catch (Exception e) {
			}
			this.totalCount = 0;
		}

		public int getPageCount() {
			return pageCount;
		}

		public void setPageCount(int pageCount) {
			this.pageCount = pageCount;
		}

		public int getPageNo() {
			if (pageNo <= 0) {
				return 1;
			} else {
				return pageNo > pageCount ? pageCount : pageNo;
			}
		}

		public List<?> getResults() {
			return results;
		}

		public void setResults(List<?> results) {
			this.results = results;
		}

		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}

		public int getCurrentPage() {
			return currentPage;
		}

		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize <= 0 ? 10 : pageSize;
		}

		public int getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}

		public void resetPageNo() {
			pageNo = currentPage + 1;
			pageCount = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		}

	}
