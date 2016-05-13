//请不要修改本文件�?
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
		int numOfOffset = 0;
		String result = "";
		ArrayList<Integer> charlist = new ArrayList<Integer>();
		
		Stack<Character> stack1 = new Stack<Character>();
		Stack<Character> stack2 = new Stack<Character>();
		stack1.clear();
		stack1.push('[');
		stack2.clear();
		stack2.push(']');

		/*
		 * for (int ptrOfCode = 0; ptrOfCode < code.length(); ptrOfCode++) { if
		 * (code.charAt(ptrOfCode) == '>') { numOfOffset++; } }
		 */

		// 以下字段填充charlist�?
		for (int i = 0; i < 200; i++) {
			charlist.add(0);
		}

		// 以下字段编译BF代码�?
		for (int ptrOfCode = 0; ptrOfCode < code.length(); ptrOfCode++) {
			switch (code.charAt(ptrOfCode)) {
			case ',':
				// 录入对应字符，并将输入指�?+1�?
				charlist.set(ptrOfCharlist, (int) param.charAt(ptrOfParam));
				ptrOfParam++;
				break;
			case '+':
				charlist.set(ptrOfCharlist, (int) (charlist.get(ptrOfCharlist) + 1));
				break;
			case '-':
				charlist.set(ptrOfCharlist, (int) (charlist.get(ptrOfCharlist) - 1));
				break;
			case '>':
				ptrOfCharlist++;
				break;
			case '<':
				ptrOfCharlist--;
				break;
			case '.':
				result += (char) (Integer.parseInt(charlist.get(ptrOfCharlist).toString()));
				break;
			case '[':

				for (int i = ptrOfCode; i < code.length(); i++) {
					if (stack1.isEmpty()) {
						if (code.charAt(i) == '[' || code.charAt(i) == ']') {
							stack1.push(code.charAt(i));
						}
					} else if (stack1.peek() == '[' && code.charAt(i) == ']') {
						stack1.pop();
						if ((int) charlist.get(ptrOfCharlist) == 0) {
							ptrOfCode = i - 1;
							break;
						}

					} else {
						if (code.charAt(i) == '[' || code.charAt(i) == ']') {
							stack1.push(code.charAt(i));
						}
					}
				}
				break;
			case ']':

				for (int i = ptrOfCode; i > 0; i--) {
					if (stack2.isEmpty()) {
						if (code.charAt(i) == '[' || code.charAt(i) == ']') {
							stack2.push(code.charAt(i));
						}
					} else if (stack2.peek() == ']' && code.charAt(i) == '[') {
						stack2.pop();
						if ((int) charlist.get(ptrOfCharlist) != 0) {
							ptrOfCode = i - 1;
							break;
						}

					} else {
						if (code.charAt(i) == '[' || code.charAt(i) == ']') {
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
