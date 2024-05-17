package csc402;

public class QuickUnion {
	// Implementing Weighted QU will require changes here
	private int[] id;
	private int components;

	private int[] sz;

	// Implementing Weighted QU will require changes here
	public QuickUnion(int N) {
		components = N;
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}

	public int getSize() {
		return this.id.length;
	}

	// You should implement Path Compression here
	private int root(int i) {
		while (i != this.id[i]) {
			id[i] = id[id[i]];
			i = this.id[i];
		}
		return i;
	}

	public boolean connected(int p, int q) {
		return this.root(p) == this.root(q);
	}

	// You should implement Weighted Quick Union here
	public void union(int p, int q) {
		int i = this.root(p);
		int j = this.root(q);

		if(i == j) {
			return;
		}

		if (sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];
		} else {
			id[j] = i;
			sz[i] += sz[j];

		}
		components --;
	}

	public int find(int p) {
		return this.id[p];
	}

	public int count() {
		return this.components;
	}
}
