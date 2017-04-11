/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test;

import com.opensymphony.xwork2.Action;

/**
 *
 * @author stpl
 */
public class TestAction implements Action{

    @Override
    public String execute() throws Exception {
        System.out.println("Test Successful");
        return SUCCESS;
    }

}
