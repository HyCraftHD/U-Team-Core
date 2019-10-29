package info.u_team.u_team_core.data;

import java.io.*;
import java.nio.file.*;
import java.util.Objects;

import org.apache.logging.log4j.*;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;

import info.u_team.u_team_core.util.GsonUtil;
import net.minecraft.data.*;

public abstract class CommonProvider implements IDataProvider {
	
	protected static final Logger LOGGER = LogManager.getLogger("DataGenerator");
	protected static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	
	protected final Marker marker;
	
	protected final DataGenerator generator;
	protected final Path path;
	
	public CommonProvider(DataGenerator generator) {
		this.generator = generator;
		this.path = resolvePath(generator.getOutputFolder());
		marker = MarkerManager.getMarker(getName());
	}
	
	protected abstract Path resolvePath(Path outputFolder);
	
	protected Path resolveAssets(Path outputFolder, String modid) {
		return outputFolder.resolve("assets").resolve(modid);
	}
	
	protected Path resolveData(Path outputFolder, String modid) {
		return outputFolder.resolve("data").resolve(modid);
	}
	
	public static void write(DirectoryCache cache, JsonElement element, Path path) throws IOException {
		write(cache, element, path, GSON);
	}
	
	public static void write(DirectoryCache cache, JsonElement element, Path path, Gson gson) throws IOException {
		try (final StringWriter writer = new StringWriter(); //
				final JsonWriter jsonWriter = GsonUtil.createTabWriter(gson, writer)) {
			GSON.toJson(element, jsonWriter);
			write(cache, writer.toString(), path);
		} catch (IOException e) {
			throw new JsonIOException(e);
		}
	}
	
	public static void write(DirectoryCache cache, String string, Path path) throws IOException {
		final String hash = HASH_FUNCTION.hashUnencodedChars(string).toString();
		if (!Objects.equals(cache.getPreviousHash(path), hash) || !Files.exists(path)) {
			Files.createDirectories(path.getParent());
			try (final BufferedWriter writer = Files.newBufferedWriter(path)) {
				writer.write(string);
			}
		}
		cache.recordHash(path, hash);
	}
}
