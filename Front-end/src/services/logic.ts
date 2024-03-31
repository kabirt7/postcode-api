import { PostcodeDataPair } from "./interfaces.ts";

const API_URL = "http://localhost:8080/postcode";

export const addData = async (
  postcode: Number,
  suburb: string
): Promise<PostcodeDataPair> => {
  console.log(postcode, "postcode");
  console.log(suburb);
  try {
    const response = await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ suburbName: suburb, postcodeNumber: postcode }),
    });

    if (!response.ok) {
      const errorMessage = `Failed to add item. Status: ${response.status} ${response.statusText}`;
      console.error(errorMessage);
      throw new Error(errorMessage);
    }

    return await response.json();
  } catch (error) {
    console.log(error);
    throw error;
  }
};
