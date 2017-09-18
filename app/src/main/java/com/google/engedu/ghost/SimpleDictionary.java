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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
              words.add(line.trim());
        }
    }
 /*   public String binarySearch(int lb, int ub, String s){

        String midWord;



            while (ub != mid) {
                 midWord=words.get(mid);
                if (s.compareTo(midWord) > 0){
                    lb = mid + 1;
                    mid=lb+ub/2;
                }
                else if(s.compareTo(midWord)==0)
                    return midWord;
                else{
                    ub = mid - 1;
                    mid=lb+ub/2;
            }}

        return null;
    }
*/
 public String binarySearch(int lb, int ub, String s){

     ArrayList <String> full=new ArrayList<>();
     if(lb<ub) {
         int mid = (ub + lb) / 2;
         String midWord = words.get(mid);
         int i = midWord.compareToIgnoreCase(s);
         if (midWord.startsWith(s)) {
             for(int a=mid;words.get(a).startsWith(s);a--){
                 full.add(words.get(a));
             }
             for(int a=mid;words.get(a).startsWith(s);a++){
                 full.add(words.get(a));
             }
             int sizze=full.size();
            int  r=new Random().nextInt(sizze);

             return words.get(r);
         } else if (i > 0) {
             return binarySearch(lb, mid - 1, s);
         } else if (i < 0) {
             return binarySearch(mid + 1, ub, s);
         } else
             return midWord;
     }
     return null;













 }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        if(prefix.equals("")){
            int r=new Random().nextInt(words.size());
            return words.get(r);
        }

        else{
            int length=words.size();
            return binarySearch(0,length-1,prefix);
        }


    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        if(prefix.equals("")){
            int r=new Random().nextInt(words.size());
            return words.get(r);
        }

        else{
            int length=words.size();
            return binarySearch(0,length-1,prefix);
        }


    }
}
