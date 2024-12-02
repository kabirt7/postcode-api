import { PostcodeDataPair } from "./interfaces.ts";

const API_URL =
  window.location.hostname === "localhost"
    ? "http://localhost:8080/postcode"
    : "http://spring-boot-app:8080/postcode";

const apostropheStringToList = (input: string) => {
  return input.split(",").map((item) => item.trim());
};

export const addData = async (
  postcode: string,
  suburb: string
): Promise<PostcodeDataPair> => {
  try {
    const suburbs = apostropheStringToList(suburb).map((suburbName) => ({
      suburbName: suburbName,
    }));

    const response = await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        suburbs: suburbs,
        postcodeNumber: postcode,
      }),
    });

    if (!response.ok) {
      const errorMessage = `Failed to add item`;
      throw new Error(errorMessage);
    }

    return await response.json();
  } catch (error) {
    console.log(error);
    throw error;
  }
};

export const getAllData = async (): Promise<PostcodeDataPair[]> => {
  try {
    const response = await fetch(API_URL);
    if (!response.ok) {
      const errorMessage = `Failed to get posts. Status: ${response.status} ${response.statusText}`;
      console.error(errorMessage);
      throw new Error(errorMessage);
    }
    const data: PostcodeDataPair[] = await response.json();

    const transformedData: PostcodeDataPair[] = data.map((item: any) => ({
      postcodeNumber: item.postcodeNumber,
      suburbs: Array.isArray(item.suburbs) ? item.suburbs : [],
      createdAt: item.createdAt,
      updatedAt: item.updatedAt,
    }));

    console.log(transformedData);
    return transformedData;
  } catch (error) {
    console.log(error);
    throw error;
  }
};

export const getPostcodeOrSuburb = async (
  type: string,
  query: number | string
): Promise<string | number | undefined> => {
  try {
    let response: Response;

    if (type === "POSTCODE") {
      response = await fetch(`${API_URL}/number/${query}`);
    } else if (type === "SUBURB") {
      response = await fetch(`${API_URL}/name/${query}`);
    } else {
      throw new Error("Invalid type");
    }

    if (!response.ok) {
      const errorMessage = `Failed to get data. Status: ${response.status} ${response.statusText}`;
      console.error(errorMessage);
      throw new Error(errorMessage);
    }

    let data: string;
    if (type === "POSTCODE") {
      data = await response.json();
      console.log(data);
      return data;
    }
    if (type === "SUBURB") {
      data = await response.text();
      console.log(data);
      return data;
    }
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const deleteData = async (postcode: Number) => {
  try {
    const response = await fetch(`${API_URL}/${postcode}`, {
      method: "DELETE",
    });
    if (!response.ok) {
      const errorMessage = `Failed to delete item. Status: ${response.status} ${response.statusText}`;
      console.error(errorMessage);
      throw new Error(errorMessage);
    }
  } catch (error) {
    console.log(error);
    throw error;
  }
};
