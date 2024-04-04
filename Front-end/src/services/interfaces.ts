export interface Suburb {
  id: Number;
  suburbName: string;
  postcode: Number;
}

export interface PostcodeDataPair {
  postcodeNumber: number;
  suburbs: Suburb[];
  createdAt?: string;
  updatedAt?: string;
}
