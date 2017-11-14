import java.sql.*;
import com.opensymphony.xwork2.config.entities.Parameterizable;

public class RemoveShowAction extends ActionSupport implements Parameterizable {
	public int showid = -1;
	public ShowStore currentlyEditedShow;
	private Map<String, String> params = new HashMap<String, String>();

	public void addParam(String key, String value) {
		this.params.put(key, value);
	}

	public Map<String, String> getParams() {
		return this.params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String execute() {
		ValueStack stack = ActionContext.getContext().getValueStack();
		// Object value = stack.findValue("showid");

		int showid = Integer.parseInt(ServletActionContext.getRequest().getParameter("showid"));

		if (Utils.isLoggedIn() && showid != -1) {
			currentlyEditedShow = Utils.getShow(showid);

			return "success";
		}

		Map<String, Object> context = new HashMap<String, Object>();

		context.put("errorMsg", new String("You must be logged in to edit a show. (showid = " + showid + ")"));
		stack.push(context);

		return "error";
	}

	public void finishEdit() {

	}

}
