package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import textprocessor.Constants;
import textprocessor.EditDistance;
import textprocessor.FileSearch;

/**
 * Servlet implementation class InteractionHandler
 */

public class RequestHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext servContext = getServletContext();
		String url = "/index.jsp";
		String action = request.getParameter("action");
		long statTime = System.currentTimeMillis();

		if (action.equals("index")) {
			String keyword = request.getParameter("keyword");
			if (keyword != null && !keyword.equals("")) {
				List<Entry<String, Integer>> files = new FileSearch(Constants.INPUT_HTML_FILE_LOCATION).search(keyword);

				if (files.size() != 0) {
					request.setAttribute("files", files);
				} else {
					EditDistance spell = new EditDistance();
					String suggest = spell.printSuggestions(keyword);
					request.setAttribute("suggest", suggest);
				}

			}
			url = "/index.jsp";
		}

		else if (action.equals("phonenumber")) {
			String phonenumber = request.getParameter("phonenumber");
			String valid;
			if (phonenumber != null && !phonenumber.equals("")) {
				String pattern = "(\\()?(\\d){3}(\\))?[- ](\\d){3}-(\\d){4}";// ^[A-Za-z0-9+_.-]+@(.+)
				// ([a-z0-9])*[@
				Pattern r = Pattern.compile(pattern);
				String str = "";
				ArrayList<String> fileContentCount = new ArrayList<String>();
				if (r.matcher(phonenumber).matches()) // pattern matching
				{

					valid = "valid";

					List<String> files = new FileSearch(Constants.INTERMEDIATE_HTML_TEXT_FILE_LOC)
							.searchphonenumber(phonenumber);

					request.setAttribute("files", files);

				} else {
					valid = "invalid";

				}
				request.setAttribute("valid", valid);
				url = "/phonenumber.jsp";
			}
		} else if (action.equals("email")) {
			String email = request.getParameter("email");
			String valid;
			if (email != null && !email.equals("")) {
				/*
				 * String pattern =
				 * "(\\()?(\\d){3}(\\))?[- ](\\d){3}-(\\d){4}";//^[A-Za-z0-9+_.-]+@(.+)
				 * //([a-z0-9])*[@
				 */
				String pattern = "^[A-Za-z0-9+_.-]+@(.+)";
				Pattern r = Pattern.compile(pattern);
				String str = "";
				ArrayList<String> fileContentCount = new ArrayList<String>();
				if (r.matcher(email).matches()) // pattern matching
				{

					valid = "valid";

					List<String> emails = new FileSearch(Constants.INTERMEDIATE_HTML_TEXT_FILE_LOC)
							.searchemail(email);
					if (emails.size() != 0)
						request.setAttribute("emails", emails);

				} else {
					valid = "invalid";

				}
				request.setAttribute("valid", valid);
				url = "/email.jsp";
			}

		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - statTime;
		String stotalTime = Long.toString(totalTime) + " milliseconds";
		request.setAttribute("totaltime", stotalTime);
		servContext.getRequestDispatcher(url).forward(request, response);
	}
}
