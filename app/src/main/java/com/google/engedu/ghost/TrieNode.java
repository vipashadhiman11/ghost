/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.ghost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class TrieNode {
    private HashMap<String, TrieNode> children;
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    public void add(String s) {
     TrieNode currentNode = this;
        for(int i=0; i<s.length();i++){
            if(!currentNode.children.containsKey(String.valueOf(s.charAt(i)))){
                currentNode.children.put(String.valueOf(s.charAt(i)),new TrieNode());
            }
            currentNode=currentNode.children.get(String.valueOf(s.charAt(i)));
        }
        currentNode.isWord=true;
    }

    public boolean isWord(String s)
    {
        TrieNode currentNode= this;
        for (int i=0;i<s.length();i++){
            if(!currentNode.children.containsKey(String.valueOf(s.charAt(i))))
                return false;
            currentNode=currentNode.children.get(String.valueOf(s.charAt(i)));
        }
        return  currentNode.isWord;

    }

    public String getAnyWordStartingWith(String s) {
        TrieNode currentNode= this;
        String newString ="";
        for (int i=0;i<s.length();i++) {
            if (!currentNode.children.containsKey(String.valueOf(s.charAt(i))))
                return null;
            newString += s.charAt(i);
            currentNode = currentNode.children.get(String.valueOf(s.charAt(i)));
        }
        if (currentNode.children.size()==0) return null;
        do {
            for (char i='a';i<='z';i++){
                if(currentNode.children.containsKey(String.valueOf(i))) {
                    newString += i;
                    currentNode = currentNode.children.get(String.valueOf(s.charAt(i)));
                }
            }

        }while (!currentNode.isWord);
        return null;
    }

    public String getGoodWordStartingWith(String s) {
        TrieNode currentNode= this;
        String child="";
        String newString ="";
        for (int i=0;i<s.length();i++) {
            if (!currentNode.children.containsKey(String.valueOf(s.charAt(i))))
                return null;
            newString += s.charAt(i);
            currentNode = currentNode.children.get(String.valueOf(s.charAt(i)));
        }
        if (currentNode.children.size()==0)
            return null;
        do {
            for (char i='a';i<='z';i++) {
                if (currentNode.children.containsKey(String.valueOf(i))) {
                    child += i;
                }
            }
            if(child=="")
                return null;
            else {
                int size = child.length();
                int r=new Random().nextInt(size);
                String c=String.valueOf(child.charAt(r));
                newString = newString + c;
                currentNode = currentNode.children.get(String.valueOf(c));
                child="";
            }
        }while (!currentNode.isWord);
        return newString;
    }
}
