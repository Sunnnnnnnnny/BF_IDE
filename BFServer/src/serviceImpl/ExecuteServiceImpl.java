//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Stack;

import service.ExecuteService;
import service.UserService;

public class ExecuteServiceImpl implements ExecuteService {

	/**
	 * 请实现该方法
	 */
	@Override
	public String execute(String code, String param) throws RemoteException {
		// TODO Auto-generated method stub
		int ptrOfParam = 0;// 输入字段指针
		int ptrOfCharlist = 0;// 运行字段指针
		int numOfOffset = 0;// 计算偏移量（没有用到）
		String result = "";
		ArrayList<Integer> charlist = new ArrayList<Integer>();

		// 利用栈来匹配对应[]；
		Stack<Character> stack1 = new Stack<Character>();
		Stack<Character> stack2 = new Stack<Character>();
		stack1.clear();
		stack2.clear();

		/*
		 * for (int ptrOfCode = 0; ptrOfCode < code.length(); ptrOfCode++) { if
		 * (code.charAt(ptrOfCode) == '>') { numOfOffset++; } }
		 */

		// 以下字段初始化charlist；
		for (int i = 0; i < 200; i++) {
			charlist.add(0);
		}

		/*
		 * 以下字段编译BF代码；
		 */
		for (int ptrOfCode = 0; ptrOfCode < code.length(); ptrOfCode++) {

			switch (code.charAt(ptrOfCode)) {

			case ',':
				// 录入对应字符，并将输入指针+1；
				charlist.set(ptrOfCharlist, (int) param.charAt(ptrOfParam));
				ptrOfParam++;
				break;

			case '+':
				// 运行字段指针所指向的数字+1；
				charlist.set(ptrOfCharlist, (int) (charlist.get(ptrOfCharlist) + 1));
				break;

			case '-':
				// 运行字段指针所指向的数字-1；
				charlist.set(ptrOfCharlist, (int) (charlist.get(ptrOfCharlist) - 1));
				break;

			case '>':
				// 运行字段指针+1，向右偏移1；
				ptrOfCharlist++;
				break;

			case '<':
				// 运行字段指针-1，向左偏移1；
				ptrOfCharlist--;
				break;

			case '.':
				// 输出结果；
				result += (char) (Integer.parseInt(charlist.get(ptrOfCharlist).toString()));
				break;

			case '[':

				// 利用出栈入栈匹配括号；
				stack1.push('[');
				for (int i = ptrOfCode + 1; i < code.length(); i++) {
					// 遍历从'['开始的代码；
					if (stack1.isEmpty()) {
						// 栈为空时结束，代码指针作出相应改变；
						if ((int) charlist.get(ptrOfCharlist) == 0) {
							ptrOfCode = i;
						}
						break;
					} else if (stack1.peek() == '[' && code.charAt(i) == ']') {
						// 如果匹配，出栈；
						stack1.pop();
					} else {
						// 如果不匹配，继续压栈；
						if (code.charAt(i) == '[') {
							stack1.push(code.charAt(i));
						}
					}
				}
				break;

			case ']':

				// 同上一个case；
				stack2.push(']');
				for (int i = ptrOfCode - 1; i >= 0; i--) {

					if (stack2.isEmpty()) {
						if ((int) charlist.get(ptrOfCharlist) != 0) {
							ptrOfCode = i;
						}
						break;

					} else if (stack2.peek() == ']' && code.charAt(i) == '[') {
						stack2.pop();
					} else {
						if (code.charAt(i) == ']') {
							stack2.push(code.charAt(i));
						}
					}
				}
				break;

			case ' ':
				ptrOfCode++;
				break;

			default:
				System.out.println("Wrong code, please check!");

			}
		}
		return result;
	}

}
