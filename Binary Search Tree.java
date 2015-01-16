package chapter2.s3;

public class Exercises_2_3_5<T> {
	
	class BSTree {
		T element;
		BSTree left, right;
		
		BSTree add(T element) {
			if (this.element == null) {
				this.element = element;
				return this;
			} else if (element.equals(this.element)) {
				return null;
			} else if (element.hashCode() < this.element.hashCode()) { // add to left
				if (left == null) {
					left = new BSTree();
				}
				return left.add(element);
			} else { // add to right
				if (right == null) {
					right = new BSTree();
				}
				return right.add(element);
			}
		}
		
		void add(BSTree tree) {
			if (tree != null && tree.element != null) {
				add(tree.element);
				add(tree.left);
				add(tree.right);
			}
		}
		
		BSTree find(T element) {
			if (element == null || this.element == null) 
				return null;
			else if (element.equals(this.element)) {
				return this; // found it!
			} else if (element.hashCode() < this.element.hashCode()) { // should be in left subtree
				if (left == null)
					return null;
				else
					return left.find(element);
			} else { // shoudl be in right subtree
				if (right == null)
					return null;
				else 
					return right.find(element);
			}
		}
		
		public String toString() {
			if (element == null)
				return "";
			else
				return ((left == null || left.element == null) ? "" : (left + ", ")) + element + ((right == null || right.element == null) ? "" : (", "+right));
		}
	}

	public static void main(String[] args) {
		

	}

}
