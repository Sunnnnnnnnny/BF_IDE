package ui;

import java.util.ArrayList;

public class Undo {
	
	// 此类为撤回和重做操作的实现

	String initialCode = "";
	ArrayList<String> undo = new ArrayList<String>();// 撤回操作的List
	ArrayList<String> redo = new ArrayList<String>();// 重做操作的List

	public Undo(String code) {
		initialCode = code;
		undo.add(initialCode);
	}

	// 保存代码版本以便撤销
	public void save(String code) {
		undo.add(code);
	}

	public void redoSave(String code) {
		redo.add(code);
	}

	public String undo() {
		// 撤回操作
		if (undo.size() - 1 >= 0) {
			String s = undo.get(undo.size() - 1);
			redo.add(undo.get(undo.size() - 1));
			undo.remove(undo.size() - 1);
			return s;
		} else {
			return initialCode;
		}

	}

	public String redo() {
		// 重做操作
		if (redo.size() - 2 >= 0) {
			String s = redo.get(redo.size() - 2);
			redo.remove(redo.size() - 1);
			return s;
		} else {
			return undo.get(undo.size() - 1);
		}

	}

	public String getCode() {
		return undo.get(undo.size() - 1);
	}

}
