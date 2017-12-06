package org.apache.struts.pac.action;

import org.apache.struts.pac.model.Show;
import org.apache.struts.pac.other.Utils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.*;

public class ViewShowAction extends ActionSupport {
	Show show;

	int helloCount = 5;

	public String execute() {
		return "success";
	}

	public Show getShow() {
		return show;
	}
}
