/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nathan.controller;

import org.json.JSONObject;
import org.json.XML;

/**
 *
 * @author manzambi.n
 */
public class Test {

    public void test_00(String[] str) {
        for (String str0 : str) {
            JSONObject json = new JSONObject(str0);
            String xml = XML.toString(json);
            
            System.out.println("Amen");
        }
    }
}
