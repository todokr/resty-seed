package io.github.todokr.resty_seed

import javax.servlet.annotation.WebServlet

import com.github.takezoe.resty.servlet.FileResourceServlet

@WebServlet(name="FileResourceServlet", urlPatterns=Array(""))
class MyFileResourceServlet extends FileResourceServlet("public")


