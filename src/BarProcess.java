class BarProcess {
	long total;
	double current;
	double proxim;
	double part;
	BarProcess(long total) {
		current = 0;
		this.total = total;
		part = total / 39;
		proxim = part;
	}
	synchronized public void init() {
		System.out.print(" [........................................]\r");
		System.out.print(" [Ü");
	}
	synchronized public void next() {
		current ++;
		if ( current > proxim && proxim < total) {
			System.out.print("Ü");
			proxim += part;
		}
	}
}
