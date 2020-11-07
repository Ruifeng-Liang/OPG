

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

/**
 * @Classname OPG
 * @Description TODO
 * @Date 2020/11/7 11:14
 * @Created by lrf
 */
public class OPG {
    static HashMap <Character,Integer>nt=new HashMap();
    static int [][] priority={{2,1,1,2,1,1},{-1,1,-1,-1,1,1},{-1,1,1,-1,1,1},{-1,-1,-1,-1,0,2},{2,1,1,2,1,1},{-1,-1,-1,-1,2,0}};
    static Stack<Character> stack=new Stack<>();
    public static String readbychar(String filename) {
        StringBuilder re = new StringBuilder();
        File file = new File(filename);
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                re.append((char) tempchar);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re.toString();
    }
    public static void main(String[] args) {
       String msg=readbychar(args[0]);
        System.out.println(msg);
       StringBuilder stringBuilder=new StringBuilder(msg);
       stringBuilder.append('#');
       stack.push('#');
       nt.put('i',0);
       nt.put('+',1);
       nt.put('*',2);
       nt.put('(',3);
       nt.put(')',4);
       nt.put('#',5);
       int j=0;
       while(j<stringBuilder.length()){
           char curchar=stringBuilder.charAt(j);
           int topnt=stack.size()-1;
           while(!nt.containsKey(stack.get(topnt))){
               topnt--;
           }
           if(!nt.containsKey(curchar)){
               System.out.println("E");
               break;
           }
           int x1=nt.get(stack.get(topnt));
           int y1=nt.get(curchar);
           if(priority[x1][y1]==2){
               System.out.println("E");
               break;
           }
           if(priority[x1][y1]==-1){
               System.out.println("I"+curchar);
               stack.push(curchar);
               j++;
               if(stack.size()==2&&stack.peek()=='E'&&j==stringBuilder.length()-1){
                   break;
               }
               continue;
           }
           if(priority[x1][y1]==1){
               if(stack.get(topnt)=='i'){
                   stack.pop();
                   stack.push('E');
                   System.out.println("R");
                   if(stack.size()==2&&stack.peek()=='E'&&j==stringBuilder.length()-1){
                       break;
                   }
                   continue;
               }
               if(stack.get(topnt)!='i'){
                   if(topnt==stack.size()-2){
                   stack.pop();
                   stack.pop();
                   stack.pop();
                   stack.push('E');
                   System.out.println("R");
                   }else {
                       System.out.println("RE");
                       break;
                   }
               }

               }
           if(priority[x1][y1]==0){
               if(topnt==stack.size()-2) {
                   stack.pop();
                   stack.pop();
                   stack.push('E');
                   System.out.println("R");
                   j++;
               }else {
                   System.out.println("RE");
               }
           }
           if(stack.size()==2&&stack.peek()=='E'&&j==stringBuilder.length()-1){
               break;
           }

       }


    }

}
