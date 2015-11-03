package java_nio;

import org.apache.log4j.Logger;

import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class TWatchService
{
    private static final Logger LOG = Logger.getLogger(TWatchService.class);

    public static void main(final String ... args)
    {
        try (WatchService watchService = FileSystems.getDefault().newWatchService())
        {
            Path watchedPath1 = Paths.get("C:/-/-data_rd_2/T_Java8/junk/a");
            Path watchedPath2 = Paths.get("C:/-/-data_rd_2/T_Java8/junk/b");

            final Map<WatchKey, Path> mapWatchKeyToPath = new HashMap<>();
            mapWatchKeyToPath.put(watchedPath1.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY), watchedPath1);
            mapWatchKeyToPath.put(watchedPath2.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY), watchedPath2);

            WatchKey watchKey;
            do {
                watchKey = watchService.take();
                final Path eventDir = mapWatchKeyToPath.get(watchKey);
                for (WatchEvent<?> watchEvent : watchKey.pollEvents())
                {
                    final WatchEvent.Kind<?> eventKind = watchEvent.kind();
                    final Path eventPath = (Path)watchEvent.context();
                    LOG.trace("eventDir=" + eventDir + ", eventKind=" + eventKind + ", eventPath=" + eventPath);
                }
            } while (watchKey.reset());

        }
        catch (Exception e)
        {
            LOG.error("e=" + e);
        }
    }
    static
    {
        (new Thread()).interrupt();
    }
}
