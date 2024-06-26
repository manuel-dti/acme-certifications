<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="departments" requestURI="${requestURI}" id="row">
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="department/administrator/edit.do?departmentId=${row.id}">
				<spring:message	code="department.edit" />
			</a>
		</display:column>		
	</security:authorize>
	
	<spring:message code="department.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="department.building" var="buildingHeader" />
	<display:column property="building" title="${buildingHeader}" sortable="false" />
	
</display:table>

<!-- Action links -->

<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="department/administrator/create.do"> <spring:message code="department.create" />
		</a>
	</div>
</security:authorize>

