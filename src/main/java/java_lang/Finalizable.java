package java_lang;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Code from https://plumbr.eu/blog/debugging-to-understand-finalizer
 * Demo of finalize issues.
 */
public class Finalizable
{
    static AtomicInteger aliveCount = new AtomicInteger(0);

   	Finalizable() {
   		aliveCount.incrementAndGet();
   	}

   	@Override
   	protected void finalize() throws Throwable {
   		aliveCount.decrementAndGet();
   	}

   	public static void main(String args[]) {
   		for (int i = 0;; i++) {
   			Finalizable f = new Finalizable();
   			if ((i % 100_000) == 0) {
   				System.out.format("After creating %d objects, %d are still alive.%n", i, aliveCount.get());
   			}
   		}
   	}
}
